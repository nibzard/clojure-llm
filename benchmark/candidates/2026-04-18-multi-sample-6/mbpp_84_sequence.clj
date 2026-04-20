(defn sequence
  "	Write a function to find the nth number in the newman conway sequence."
  [n]
  (cond
    (nil? n) nil
    (not (pos? n)) nil
    (= n 1) 1
    (= n 2) 1
    :else
    (let [vals (loop [i 3
                      acc [0 1 1]]
                 (if (> i n)
                   acc
                   (let [p-prev (nth acc (dec i))
                         p-pprev (nth acc (- i 2))
                         next-val (+ (nth acc p-prev)
                                     (nth acc (- i p-pprev)))]
                     (recur (inc i) (conj acc next-val)))))]
      (nth vals n))))