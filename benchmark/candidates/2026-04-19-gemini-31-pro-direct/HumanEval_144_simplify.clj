(defn simplify [x n]
  (let [parse (fn [s] (map bigint (clojure.string/split s #"/")))
        [a b] (parse x)
        [c d] (parse n)]
    (zero? (mod (* a c) (* b d)))))