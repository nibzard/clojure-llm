(defn reverse_delete
  [s c]
  (let [filtered (apply str (remove (set c) s))
        palindrome? (= filtered (clojure.string/reverse filtered))]
    [filtered palindrome?]))