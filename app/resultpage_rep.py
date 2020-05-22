import sys
import re

new_delete_block = 0
with open(sys.argv[1], 'r') as f:
  lines = f.readlines()
  for i in range(len(lines)):
    line = lines[i]
    if re.search(r'SWIGEXPORT .* Java_im_floo_floolib_flooJNI_new', str(line)) or re.search(r'SWIGEXPORT .* Java_im_floo_floolib_flooJNI_delete', str(line)) :
      new_delete_block = 1
    if re.search(r'^}', str(line)):
      new_delete_block = 0

    m1 = re.match(r'([ ]*)(floo::BMXResultPage< .*)(\*arg.*) = .* 0 ;',str(line))
    m2 = re.match(r'(.*arg.[ ]*= \*\()(floo::BMXResultPage\< .*) (\*\*.*)',str(line))
    m3 = re.match(r'(.*result[ ]*=[ ]*.*)\(floo::BMXResultPage<.*\*\)(arg.\))(.*;)',str(line))
    if m1 and new_delete_block == 0:
      print m1.group(1) + "std::shared_ptr< " + m1.group(2) + ">" + m1.group(3) + " = 0 ;"
    elif m2 and new_delete_block == 0:
      print m2.group(1) + "std::shared_ptr< " + m2.group(2) + ">" + m2.group(3)
    elif m3 and new_delete_block == 0:
      print m3.group(1) + m3.group(2) + "->get()" + m3.group(3)
    else :
      print line,
