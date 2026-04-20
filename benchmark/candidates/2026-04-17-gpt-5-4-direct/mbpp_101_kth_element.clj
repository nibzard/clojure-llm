(defn kth_element
  "	Write a function to find the kth element in the given vector using 1-based indexing."
  [arr k]
  (when (and (vector? arr)
             (integer? k)
             (<= 1 k (count arr)))
    (nth arr (dec k))))