(defn problem-juxtaposition
  [& fs]
  (fn [& args]
    (vec (map #(apply % args) fs))))