(defn lcs_of_three
  "Finds the length of the longest common subsequence of three strings."
  [X Y Z]
  (let [m (count X)
        n (count Y)
        o (count Z)
        Xv (vec X)
        Yv (vec Y)
        Zv (vec Z)
        full-dp (reduce
                 (fn [dp i]
                   (conj dp
                         (reduce
                          (fn [dp-i j]
                            (conj dp-i
                                  (reduce
                                   (fn [dp-ij k]
                                     (let [val (if (or (zero? i) (zero? j) (zero? k))
                                                 0
                                                 (if (and (= (Xv (dec i)) (Yv (dec j)))
                                                          (= (Xv (dec i)) (Zv (dec k))))
                                                   (inc (get-in dp [(dec i) (dec j) (dec k)]))
                                                   (max (get-in dp [(dec i) j k])
                                                        (get-in dp-i [(dec j) k])
                                                        (dp-ij (dec k)))))]
                                       (conj dp-ij val)))
                                   []
                                   (range (inc o)))))
                          []
                          (range (inc n)))))
                 []
                 (range (inc m)))]
    (get-in full-dp [m n o])))