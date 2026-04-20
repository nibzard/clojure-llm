(defn is_Diff
  "	Write a cljthon function to find whether a number is divisible by 11."
  [n]
  (let [n (if (number? n) (bigint (if (neg? n) (- n) n)) nil)]
    (boolean
     (and n
          (zero?
           (loop [x n
                  odd-sum 0N
                  even-sum 0N
                  odd? true]
             (if (zero? x)
               (mod (- odd-sum even-sum) 11)
               (let [digit (mod x 10)]
                 (recur (quot x 10)
                        (if odd? (+ odd-sum digit) odd-sum)
                        (if odd? even-sum (+ even-sum digit))
                        (not odd?)))))))))