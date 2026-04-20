(defn upper-ctr-nested
  "Count the uppercase characters in a nested collection of strings.
  
  Examples:
  (upper-ctr-nested [\"Ab\" [\"Cde\" nil] \"FG\"]) ;=> 4
  (upper-ctr-nested nil) ;=> 0
  (upper-ctr-nested [\"abc\" [\"DEF\" [\"G\"]]]) ;=> 4"
  [x])