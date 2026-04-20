(defn is_bored [S]
  (if (empty? S)
    0
    (->> (clojure.string/split S #"[.?!]")
         (map clojure.string/trim)
         (filter #(re-find #"^I(?:\s|$)" %))
         count)))