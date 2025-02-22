import java.awt.Image;
import java.awt.Toolkit;

public class ImageUtils {
	public static Image getImage(String fileName) {
		return Toolkit.getDefaultToolkit().getImage(ImageUtils.class.getResource("/" + fileName));
	}
}
