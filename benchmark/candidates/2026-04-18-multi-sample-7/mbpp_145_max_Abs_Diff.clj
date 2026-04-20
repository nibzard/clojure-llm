(defn max_Abs_Diff
  "	Write a cljthon function to find the maximum difference between any two elements in a given vector."
  [arr]
  (when (seq arr)
    (let [mn (reduce min arr)
          mx (reduce max arr)]
      (- mx mn))))