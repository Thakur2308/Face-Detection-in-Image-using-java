package javafacedetect;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class face {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		String imgfile="Images/IMG_20201120_160248.jpg";
		
		//The Mat class of OpenCV library is used to store the values of an image. 
		//It represents an n-dimensional array and is used to store image data of grayscale or 
		//color images, voxel volumes, vector fields, point clouds, tensors, histograms, etc.
		
		Mat src= Imgcodecs.imread(imgfile);
		
		String xmlfile="xml/lbpcascade_frontalface.xml";
		
		
		//The CascadeClassifier class of is used to load the classifier file and detects the desired objects in the image.
		
		CascadeClassifier cc=new CascadeClassifier(xmlfile);
		
		
		MatOfRect facedetection= new MatOfRect();
		cc.detectMultiScale(src, facedetection);
		
		System.out.println(String.format("Detected faces: %d", facedetection.toArray().length));
		
		for(Rect rect:facedetection.toArray()) {
			Imgproc.rectangle(src, new Point(rect.x,rect.y),new Point(rect.x+rect.width, rect.y+rect.height), new Scalar(0,0,255),3);
			
		}
		Imgcodecs.imwrite("Images/IMG_20201120_160248_out.jpg", src);
		System.out.println("Image Detection finished");
		
	}
}
