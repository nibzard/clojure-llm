(defn filter_by_prefix
  "Filter an input list of strings only for ones that start with a given prefix."
  [strings prefix]
  (vec (filter #(clojure.string/starts-with? % prefix) strings)))