(defn lcs_of_three
  "	Write a function to find the longest common subsequence for the given three string sequence. https://www.geeksforgeeks.org/lcs-longest-common-subsequence-three-strings/"
  [X Y Z]
  (let [m (count X)
        n (count Y)
        o (count Z)]
    (if (or (zero? m) (zero? n) (zero? o))
      ""
      (let [dp (reduce (fn [dp i]
                         (reduce (fn [dp j]
                                   (reduce (fn [dp k]
                                             (let [ch1 (nth X (dec i))
                                                   ch2 (nth Y (dec j))
                                                   ch3 (nth Z (dec k))
                                                   new-val (if (= ch1 ch2 ch3)
                                                             (inc (get-in dp [(dec i) (dec j) (dec k)] 0))
                                                             (max (get-in dp [(dec i) j k] 0)
                                                                  (get-in dp [i (dec j) k] 0)
                                                                  (get-in dp [i j (dec k)] 0)))]
                                               (assoc-in dp [i j k] new-val)))
                                           dp (range 1 (inc o))))
                                 dp (range 1 (inc n))))
                       (vec (repeat (inc m) (vec (repeat (inc n) (vec (repeat (inc o) 0))))))
                       (range 1 (inc m)))]
        (loop [i m
               j n
               k o
               result []]
          (if (or (zero? i) (zero? j) (zero? k))
            (apply str (reverse result))
            (let [ch1 (nth X (dec i))
                  ch2 (nth Y (dec j))
                  ch3 (nth Z (dec k))]
              (if (= ch1 ch2 ch3)
                (recur (dec i) (dec j) (dec k) (conj result ch1))
                (let [best-val (get-in dp [(dec i) (dec j) (dec k)] 0)]
                  (cond
                    (= (get-in dp [(dec i) j k] 0) best-val)
                    (recur (dec i) j k result)

                    (= (get-in dp [i (dec j) k] 0) best-val)
                    (recur i (dec j