# Caculate Calibration efficiency for Ne21 pg then do a simple polynominal fitting for each detector
import numpy as np
import matplotlib.pyplot as plt

eff_data = np.genfromtxt("cali_eff_Ne21.csv",dtype=float, delimiter="\t")
Intensity = eff_data[:,1]
#print ("Intensity", Intensity)
I_error = eff_data[:,2]
#print ("I error", I_error)
gamma_energy = eff_data[:,0]
coor = [(0,0), (0,1), (1,0), (1,1), (2,0), (2,1)]

fig1,ax = plt.subplots(3,2)
fig1.subplots_adjust(hspace=0.50)
name = ["Canberra 1", "Canberra 2", "Triumf 1", "Triumf 2", "Clover 1", "Clover 2"]

for i in range (6) :
	index = 2* i + 3
	error_index = 2 * i + 4
	eff = eff_data[:,index]/Intensity
	fit_parameters = np.polyfit (gamma_energy, eff, 4)
	uncertainty = eff * np.sqrt( ((I_error / Intensity)**2) + ((eff_data[:,error_index]/eff_data[:,index])**2))
	print ("fitting parametes", fit_parameters)
	ax[coor[i]].plot(gamma_energy, eff , color='k',marker="o",linestyle="None",markersize=5, label=name[i])
	ax[coor[i]].errorbar(gamma_energy, eff, yerr = uncertainty , xerr = None, ecolor = 'b')
	p = np.poly1d (fit_parameters)
	print ("1369 efficiency of ", name[i]," is", p(1369))
	ax[coor[i]].plot(gamma_energy, p(gamma_energy), color='r',marker="o",linestyle="-",markersize=3 )
	legend = ax[coor[i]].legend(loc='upper right', shadow=True, fontsize='9')
	ax[coor[i]].set_ylabel ("Relative eff")
	ax[coor[i]].set_xlabel ("Gamma energy (keV)")
	ax[coor[i]].ticklabel_format(style='sci', axis='y') 

plt.savefig ('relative_calibration_eff.pdf')	
plt.show ()
print ("Input data", eff_data)
print ("Intensity", Intensity)
