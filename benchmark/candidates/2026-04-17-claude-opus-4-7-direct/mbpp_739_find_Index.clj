(defn find-Index
  "Write a cljthon function to find the index of smallest triangular number with n digits."
  [n]
  (if (<= n 1)
    1
    (loop [i 1]
      (let [triangular-num (/ (* i (inc i)) 2)
            num-digits (count (str triangular-num))]
        (if (>= num-digits n)
          i
          (recur (inc i)))))))