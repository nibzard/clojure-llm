(defn simplify
  [x n]
  (let [[xn xd] (map #(Long/parseLong %) (clojure.string/split x #"/"))
        [nn nd] (map #(Long/parseLong %) (clojure.string/split n #"/"))]
    (zero? (mod (* xn nn) (* xd nd)))))