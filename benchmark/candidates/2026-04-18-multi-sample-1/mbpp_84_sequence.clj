(defn sequence
  "	Write a function to find the nth number in the newman conway sequence."
  [n]
  (cond
    (nil? n) nil
    (<= n 0) nil
    (<= n 2) 1
    :else
    (loop [i 3
           vals {1 1, 2 1}]
      (if (> i n)
        (get vals n)
        (let [p-im1 (get vals (dec i))
              p-pim1 (get vals p-im1)
              p-minus-pim1 (get vals (- i p-im1))
              next-val (+ p-pim1 p-minus-pim1)]
          (recur (inc i) (assoc vals i next-val)))))))