(defn multiple_to_single
  "	Write a function to join a list of multiple integers into a single integer."
  [L]
  (if (seq L)
    (reduce (fn [acc n]
              (+ (* acc (count (str (Math/abs (long n)))))
                 (Math/abs (long n))))
            0
            L)
    0))