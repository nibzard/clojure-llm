(defn filter_by_substring
  "Filter an input list of strings only for ones that contain given substring"
  [strings substring]
  (filter #(clojure.string/includes? % substring) strings))