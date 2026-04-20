(defn file_name_check
  [file_name]
  (let [s (or file_name "")
        digits (count (filter #(Character/isDigit ^char %) s))
        parts (clojure.string/split s #"\." -1)]
    (if (and (<= digits 3)
             (= 2 (count parts))
             (not (empty? (first parts)))
             (re-matches #"[A-Za-z].*" (first parts))
             (#{"txt" "exe" "dll"} (second parts)))
      "Yes"
      "No")))