(defn closest_num
  "	Write a function to find the closest smaller number than n."
  [N]
  (when (number? N)
    (if (integer? N)
      (dec N)
      (long (Math/floor N)))))