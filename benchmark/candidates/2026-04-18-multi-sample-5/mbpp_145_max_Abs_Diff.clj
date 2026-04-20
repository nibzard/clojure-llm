(defn max_Abs_Diff
  "	Write a cljthon function to find the maximum difference between any two elements in a given vector."
  [arr]
  (when (seq arr)
    (let [mn (apply min arr)
          mx (apply max arr)]
      (Math/abs (- mx mn)))))