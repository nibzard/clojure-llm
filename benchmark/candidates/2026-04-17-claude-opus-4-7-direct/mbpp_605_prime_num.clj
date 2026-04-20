(defn prime_num
  "Write a function to check if the given integer is a prime number."
  [num]
  (cond
    (nil? num) false
    (not (integer? num)) false
    (< num 2) false
    (= num 2) true
    (even? num) false
    :else (let [limit (int (Math/sqrt num))]
            (loop [i 3]
              (cond
                (> i limit) true
                (zero? (rem num i)) false
                :else (recur (+ i 2)))))))