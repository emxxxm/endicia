import glob
import os
import zipfile


with open('ATF_COT_ALL.txt', 'w') as outfile_cot, open('ATF_PME_ORIGIN_SCHEDULE_ALL.txt', 'w') as outfile_origin, open('ATF_NON_PME_SVC_STD_ALL.txt', 'w') as outfile_npme:
	for sub_dir in os.listdir('test3DIG/'): ##this path is hard coded. Just change it whenever needed.
		for filename in os.listdir('test3DIG/'+sub_dir):
			with open(os.path.join('test3DIG/'+sub_dir, filename)) as infile:
				if "ATF_COT" in filename:
					outfile_cot.write(infile.read())
				elif "ATF_NON_PME_SVC_STD" in filename:
					outfile_npme.write(infile.read())
				elif "ATF_PME_ORIGIN_SCHEDULE" in filename:
					outfile_origin.write(infile.read())
