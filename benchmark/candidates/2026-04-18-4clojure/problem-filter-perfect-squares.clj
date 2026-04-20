(defn problem-filter-perfect-squares [a]
  (->> (clojure.string/split a #",")
       (filter (fn [s]
                 (let [n (Integer/parseInt s)
                       r (Math/sqrt n)]
                   (== r (Math/floor r)))))
       (clojure.string/join ",")))