

abstract class TemplateClass{
    public final void pipelineFlow(String path){
        loadData(path);
        preprocessData();
        trainModel();
        evaluateModel();
        saveModel();
    }

    public void loadData(String path){
        System.out.println("[Common] Load data into the model from "+ path);
    }
    public void preprocessData() {
        System.out.println("[Common] Split data into test/train and normalizing data...");
    }
    abstract void trainModel();
    abstract void evaluateModel();
    public void saveModel() {
        System.out.println("[Common] Save the model!");
    }
}

class DecisonTreeModel extends TemplateClass{
    @Override
    public void trainModel(){
        System.out.println("Train the model...");
    }

    @Override
    public void evaluateModel(){
        System.out.println("Evaluate the model performance...");
    }
}


// client
class TemplateDesignPattern{
    public static void main(String[] args){
        System.out.println("Template Design Pattern!");

        TemplateClass tc = new DecisonTreeModel();
        tc.pipelineFlow("/model/dataPath");

    }
}