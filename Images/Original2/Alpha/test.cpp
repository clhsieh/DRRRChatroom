#include <vector>
#include <string>
#include <iostream>
#include <opencv2/opencv.hpp>
#include <boost/filesystem.hpp>

using namespace std;
using namespace cv;
namespace fs=boost::filesystem;

int main(int argc, char* argv[])
{
    vector<string> files = {"bakyura.png", "chinese.png", "gg.png",     "izaya.png",
                            "kanra.png",   "koukin.png",  "lady.png",   "lolita.png",
                            "saika.png",   "setton.png",  "shinra.png", "simon.png",
                            "sizuo.png",   "tanaka.png",  "woman.png",  "zawa.png"};
    vector<Mat> plane;
    Mat white=255*Mat::ones(58, 58, CV_8UC1), output;
    for (auto file=files.begin(); file!=files.end(); file++)
    {
        string infile=(fs::path("..")/(*file)).string(), outfile=(fs::path("All")/("all-"+*file)).string(), outhead=(fs::path("Head")/("head-"+*file)).string(), outbody=(fs::path("Body")/("body-"+*file)).string();
        Mat image = imread(infile, 1), imagef, alpha=Mat::zeros(58, 58, CV_8UC1);
        image.convertTo(imagef, CV_32FC3);

        Vec3f f(255.f, 255.f, 255.f), b=imagef.at<Vec3f>(6, 6), d=f-b;
        for (int j=4; j<54; j++)
        {
            for(int i=4; i<54; i++)
            {
                Vec3f c=imagef.at<Vec3f>(j, i);
                Vec3f u=c-b;
                float a=max(u[0]/d[0], u[1]/d[1]);
                a = max(a, u[2]/d[2]);
                alpha.at<uchar>(j, i) = cvRound(a*255.0);
            }
        }

        plane.clear();
        plane.push_back(white);
        plane.push_back(white);
        plane.push_back(white);
        plane.push_back(alpha);
        merge(plane, output);

        imwrite(outfile, output);
        imwrite(outhead, output);
        imwrite(outbody, output);
    }

    Mat frame=255*Mat::ones(58, 58, CV_8UC1);
    frame(Rect(4, 4, 50, 50)).setTo(0);
    plane.clear();
    plane.push_back(white);
    plane.push_back(white);
    plane.push_back(white);
    plane.push_back(frame);
    merge(plane, output);
    imwrite("frame.png", output);

    return 0;
}
