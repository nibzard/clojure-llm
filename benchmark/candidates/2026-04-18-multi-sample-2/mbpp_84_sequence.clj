(defn sequence
  "	Write a function to find the nth number in the newman conway sequence."
  [n]
  (cond
    (nil? n) nil
    (<= n 0) nil
    (<= n 2) 1
    :else
    (let [q (loop [i 3
                   qs {1 1, 2 1}]
              (if (> i n)
                qs
                (let [q-prev (get qs (dec i))
                      q-prev-prev (get qs q-prev)
                      q-idx (- i q-prev)
                      q-other (get qs q-idx)
                      q-i (+ q-prev-prev q-other)]
                  (recur (inc i) (assoc qs i q-i)))))]
      (get q n))))