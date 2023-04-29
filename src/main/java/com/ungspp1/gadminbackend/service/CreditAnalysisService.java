import org.springframework.stereotype.Service;


@Service
public class CreditAnalysisService {

    private static final String MODEL_PATH = "ruta/al/modelo";

    public CreditAnalysis analyzeCredit(double inputBalance, double outputBalance) {
        // Cargar el modelo entrenado
        try (SavedModelBundle model = SavedModelBundle.load(MODEL_PATH, "serve")) {
            // Crear el tensor de entrada
            Tensor inputTensor = Tensor.create(new double[][]{{inputBalance, outputBalance}});

            // Ejecutar el modelo en el tensor de entrada
            Tensor result = model
                    .session()
                    .runner()
                    .feed("dense_1_input", inputTensor)
                    .fetch("dense_2/BiasAdd")
                    .run()
                    .get(0);

            // Obtener la predicción
            float[][] output = new float[1][1];
            result.copyTo(output);

            // Crear la respuesta de análisis crediticio
            CreditAnalysis analysis = new CreditAnalysis();
            analysis.setInputBalance(inputBalance);
            analysis.setOutputBalance(outputBalance);
            analysis.setApproved(output[0][0] > 0.5);

            return analysis;

        } catch (Exception e) {
            // Manejar cualquier excepción que se produzca durante la predicción
            e.printStackTrace();
            return null;
        }
    }
}
