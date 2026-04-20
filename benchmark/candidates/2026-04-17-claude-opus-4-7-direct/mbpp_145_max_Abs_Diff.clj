(defn max_Abs_Diff
  "Write a cljthon function to find the maximum difference between any two elements in a given vector."
  [arr]
  (if (empty? arr)
    0
    (let [min-val (apply min arr)
          max-val (apply max arr)]
      (- max-val min-val))))