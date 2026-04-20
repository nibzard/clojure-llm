(defn file_name_check
  [file_name]
  (let [dot-count (count (filter #(= \. %) file_name))
        digit-count (count (filter #(Character/isDigit %) file_name))
        dot-idx (.indexOf ^String file_name ".")
        valid-extensions #{"txt" "exe" "dll"}]
    (cond
      (not= 1 dot-count) "No"
      (> digit-count 3) "No"
      (neg? dot-idx) "No"
      (zero? dot-idx) "No"
      (not (Character/isLetter ^char (first file_name))) "No"
      :else (let [extension (subs file_name (inc dot-idx))]
              (if (contains? valid-extensions extension)
                "Yes"
                "No")))))