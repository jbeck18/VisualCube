package Testing;

import ImageGeneration.Generation.VisualCube;
import ImageGeneration.Attributes.BackgroundColor;
import ImageGeneration.Attributes.StageMaskType;
import ImageGeneration.Attributes.VCAttributes;

public class Examples {

    public static void main(String[] args) {
        VCAttributes attributes = new VCAttributes();
        VisualCube.saveImageWithAttributesToFile(attributes, "examples/example_1");

        attributes = new VCAttributes()
                .backgroundColor(BackgroundColor.TRANSPARENT)
                .algorithm("S2 E2 M2")
                .imageSize(250);

        VisualCube.saveImageWithAttributesToFile(attributes, "examples/example_2");

        attributes = new VCAttributes()
                .backgroundColor(BackgroundColor.TRANSPARENT)
                .algorithmCase("R U R'")
                .stageMask(StageMaskType.F2L)
                .imageSize(250);

        VisualCube.saveImageWithAttributesToFile(attributes, "examples/example_3");
    }
}
