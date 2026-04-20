(defn split-camel-words
  "Put spaces between word boundaries in a CamelCase or PascalCase string.

Examples:
  (split-camel-words \"HelloWorld\") => \"Hello World\"
  (split-camel-words \"XMLHttpRequest\") => \"XML Http Request\"
  (split-camel-words nil) => nil

Preserve existing spaces and return nil when given nil."
  [s]
  (when s
    (clojure.string/replace s #"(?<=[a-z0-9])(?=[A-Z])" " ")))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "Hello World" (split-camel-words "HelloWorld")))
  (is (= "XML Http Request" (split-camel-words "XMLHttpRequest")))
  (is (= nil (split-camel-words nil))))

(run-test test-variation)
