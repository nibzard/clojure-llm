(defn lcs_of_three
  "	Write a function to find the longest common subsequence for the given three string sequence. https://www.geeksforgeeks.org/lcs-longest-common-subsequence-three-strings/"
  [X Y Z]
  (let [xs (or X "")
        ys (or Y "")
        zs (or Z "")
        m (count xs)
        n (count ys)
        p (count zs)]
    (loop [i 0
           j 0
           k 0
           dp (vec (repeat (inc p) (vec (repeat (inc n) (vec (repeat (inc m) 0))))))]
      (if (or (>= i m) (>= j n) (>= k p))
        (apply max (flatten dp))
        (let [x (nth xs i)
              y (nth ys j)
              z (nth zs k)
              v (if (= x y z)
                  (loop [ii (inc i)
                         jj (inc j)
                         kk (inc k)]
                    (if (or (>= ii m) (>= jj n) (>= kk p))
                      (+ 1 (nth (nth (nth dp (inc k)) (inc j)) i))
                      (recur (inc ii) (inc jj) (inc kk))))
                  (reduce max
                          (loop [ii (inc i)
                                 jj (inc j)
                                 kk (inc k)]
                            (if (or (>= ii m) (>= jj n) (>= kk p))
                              []
                              (recur (inc ii) jj kk
                                     (recur ii (inc jj) kk
                                            (recur ii jj (inc kk))))))))]
          (recur (inc i) j k (assoc-in dp [k j i] v)))))))