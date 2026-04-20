(defn sequence
  "Write a function to find the nth number in the newman conway sequence."
  [n]
  (if (<= n 0)
    nil
    (loop [i 2
           a 1
           b 1]
      (if (>= i n)
        b
        (let [c (+ (a (dec (dec i)))
                   (a (dec i)))]
          (recur (inc i) b c))))))