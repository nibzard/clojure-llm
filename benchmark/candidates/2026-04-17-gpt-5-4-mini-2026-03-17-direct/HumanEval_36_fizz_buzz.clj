(defn fizz_buzz
  "Return the number of times the digit 7 appears in integers less than n which are divisible by 11 or 13."
  [n]
  (letfn [(count-7s [x]
            (count (filter #{\7} (str x))))]
    (reduce
      (fn [acc i]
        (if (or (zero? (mod i 11))
                (zero? (mod i 13)))
          (+ acc (count-7s i))
          acc))
      0
      (range n))))