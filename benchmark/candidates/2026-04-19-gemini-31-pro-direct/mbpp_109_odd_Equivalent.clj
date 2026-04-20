(defn odd_Equivalent
  "Write a clojure function to find the number of numbers with an odd value when rotating a binary string the given number of times."
  [s n]
  (count (filter #{\1} (take n s))))