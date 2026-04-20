(defn how_many_times
  " Find how many times a given substring can be found in the original string. Count overlaping cases.
  >>> (how_many_times "" "a")
  0
  >>> (how_many_times "aaa" "a")
  3
  >>> (how_many_times "aaaa" "aa")
  3"
  [string substring]
  (let [m (count substring)]
    (if (zero? m)
      0
      (let [target (seq substring)]
        (count (filter #(= target %) (partition m 1 string)))))))