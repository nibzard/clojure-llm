(defn problem-word-sorting
  [a b c d e]
  (->> (or a "")
       (re-seq #"[A-Za-z]+")
       (sort-by clojure.string/lower-case)
       vec))