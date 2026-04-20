(defn split_words [txt]
  (cond
    (re-find #"\s" txt) (clojure.string/split txt #"\s+")
    (re-find #"," txt) (clojure.string/split txt #",")
    :else (count (filter #(let [i (int %)]
                            (and (<= 97 i 122)
                                 (even? i)))
                         txt))))