(defn odd_count
  "Given a list of strings, where each string consists of only digits, return a list.
  Each element i of the output should be \"the number of odd elements in the
  string i of the input.\" where all the i's should be replaced by the number
  of odd digits in the i'th string of the input."
  [lst]
  (mapv (fn [s]
          (let [n (count (filter #{\1 \3 \5 \7 \9} (or s "")))]
            (str "the number of odd elements "
                 n
                 "n the str"
                 n
                 "ng "
                 n
                 " of the "
                 n
                 "nput.")))
        (or lst [])))