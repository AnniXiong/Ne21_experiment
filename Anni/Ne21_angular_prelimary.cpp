#include "TCanvas.h"
#include "TH1.h"
#include "TGraph.h"
#include "TAxis.h"
#include "TGaxis.h"
 
 void Ne21_angular_prelimary () {
 
 TCanvas *c = new TCanvas ("c", "canvas1", 200,10,650,500);
 TGraph *g1 = new TGraph("/Users/anni/Desktop/Ne21_experiment/Anni/Ne21_exp_angular_st.txt", "%lg %lg");
 
 Int_t const n = 6;
 
 Float_t x[6]  = {0, 30, 40, 70, 90, 100};
 Float_t y[6]  = {77202, 76113, 85281, 72933, 39619, 76181};
 
 Float_t ey[6]  = {355, 371, 325, 379, 406, 344};
 Float_t ex[6]  = {0, 0, 0, 0, 0, 0};
 
 TGraphErrors *gr = new TGraphErrors(n,x,y,ex,ey);
 gr->SetTitle("TGraphErrors");
 gr->SetMarkerColor(4);
 gr ->SetMarkerSize(0.3);
 gr->SetMarkerStyle(20);
 gr->Draw("ALP");
 //c->Update();

 g1->SetTitle ("Angular distribution of the 1369keV gamma peak");
 g1->GetYaxis()->SetTitle("Counts");
 g1->GetYaxis()->SetTitleSize(0.040);
 g1->GetYaxis()->CenterTitle();
 g1->GetYaxis()->SetLabelSize(0.030);
 g1->GetYaxis()->SetTitleOffset(1.10);
 
 g1->GetXaxis()->SetTitle("Angle (degree) w.r.t the beamline");
 g1->GetXaxis()->SetTitleOffset(0.93);
 g1->GetXaxis()->SetTitleSize(0.040);
 g1->GetXaxis()->CenterTitle();
    
 g1->GetXaxis()->SetLabelSize(0.040);
 g1->SetLineColor(2);
 g1->Draw();


 
}