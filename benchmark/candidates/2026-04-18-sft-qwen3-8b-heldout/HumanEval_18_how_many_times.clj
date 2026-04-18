(defn how_many_times
  " Find how many times a given substring can be found in the original string. Count overlaping cases.
  >>> (how_many_times \"\" \"a\")
  0
  >>> (how_many_times \"aaa\" \"a\")
  3
  >>> (how_many_times \"aaaa\" \"aa\")
  3"
  [string substring]
  (let [s (or string "")
        ss (or substring "")]
    (if (or (empty? s) (empty? ss))
      0
      (count (re-seq (re-pattern (apply str (map #(if (= % \.) "." %) ss))) s)))))