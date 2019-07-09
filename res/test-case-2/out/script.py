import numpy as np
import string

# def initiate_rates(param, size, lo, hi):
# 	out = "{}={{".format(param)
# 	for i in range(size):
# 		mean = (hi - lo)/2
# 		std = mean
# 		value = abs(np.random.normal(mean, std))
# 		if i < size - 1:
# 			out += "{},".format(value)
# 		else:
# 			out += "{}}};\n".format(value)

# 	return out

# def initiate_values(param, size, lo, hi):
# 	out = "{}={{".format(param)
# 	for i in range(size):
# 		value = np.random.uniform(lo, hi)
# 		if i < size - 1:
# 			out += "{},".format(value)
# 		else:
# 			out += "{}}};".format(value)

# 	return out

def initiate_rates(param, size, lo, hi):
	out = ""
	for i in range(size):
		mean = (hi - lo)/2
		std = mean
		value = abs(np.random.normal(mean, std))
		out += "{}[{}]={}\n".format(param, i, value)

	return out

def initiate_values(param, size, lo, hi):
	out = ""
	for i in range(size):
		value = np.random.uniform(lo, hi)
		out += "{}[{}]={}\n".format(param, i, value)

	return out

if __name__ == '__main__':

	rates_lo, rates_hi = 10e-2, 10e5
	init_lo, init_hi = 10e-10, 10e-5
	rates = {"rates_c_1":9}
	inits = {"init_c_1":35}

	file_name = "parameters.txt"

	with open(file_name, "w+") as f:
		for param, size in rates.items():
			f.write(initiate_rates(param, size, rates_lo, rates_hi))

		for param, size in inits.items():
			f.write(initiate_values(param, size, init_lo, init_hi))

