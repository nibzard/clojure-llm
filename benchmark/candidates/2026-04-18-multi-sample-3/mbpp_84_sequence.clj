(defn sequence
  "	Write a function to find the nth number in the newman conway sequence."
  [n]
  (cond
    (nil? n) nil
    (<= n 0) nil
    (<= n 2) 1
    :else
    (let [q (loop [i 3
                   qs [0 1 1]]
              (if (> i n)
                qs
                (let [prev (nth qs (dec i))
                      idx (- i prev)
                      val (+ (nth qs idx) (nth qs (inc idx)))]
                  (recur (inc i) (conj qs val)))))]
      (nth q n))))