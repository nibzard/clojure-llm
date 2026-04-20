(defn split-camel-keys
  "Return a vector of keywords split from a camelCase or PascalCase string.
  
  Consecutive capital letters should stay together as one token.
  Return nil when input is nil.
  
  Examples:
  (split-camel-keys \"userIDNumber\") ;=> [:user :id :number]
  (split-camel-keys \"HTTPServerError\") ;=> [:http :server :error]
  (split-camel-keys nil) ;=> nil"
  [s]
  (when s
    (->> (re-seq #"[A-Z]+(?=[A-Z][a-z]|[a-z]|$)|[A-Z]?[a-z]+" s)
         (map #(-> % clojure.string/lower-case keyword))
         vec)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [:user :id :number] (split-camel-keys "userIDNumber"))))

(run-test test-variation)
