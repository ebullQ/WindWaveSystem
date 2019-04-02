import service.ImageCreatorService;
import service.UpdateDataService;

public class Main {

    public static void main(String[] args) {

        new UpdateDataService().executeProcess();

        new ImageCreatorService().makeImage();

        System.out.println();
    }

}
