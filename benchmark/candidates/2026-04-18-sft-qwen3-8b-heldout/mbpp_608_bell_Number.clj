(defn bell_Number
  "	Write a cljthon function to find nth bell number."
  [n]
  (let [n (if (number? n) (long n) 0)]
    (cond
      (neg? n) nil
      (zero? n) 1
      :else
      (loop [k 1
             prev 1
             curr 1]
        (if (> k n)
          curr
          (let [next-val (+ curr (* prev k))]
            (recur (inc k) curr next-val)))))))