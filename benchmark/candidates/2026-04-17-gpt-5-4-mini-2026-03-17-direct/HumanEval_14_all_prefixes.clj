(defn all_prefixes
  "Return list of all prefixes from shortest to longest of the input string."
  [string]
  (when string
    (mapv #(subs string 0 %) (range 1 (inc (count string))))))