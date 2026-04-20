(defn count_upper [s]
  (let [upper-vowels #{\A \E \I \O \U}]
    (count (keep-indexed (fn [idx char]
                           (when (and (even? idx) (upper-vowels char))
                             char))
                         s))))