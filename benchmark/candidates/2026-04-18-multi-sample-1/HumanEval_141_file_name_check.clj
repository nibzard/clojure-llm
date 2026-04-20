(defn file_name_check
  "Create a function which takes a string representing a file's name, and returns
  'Yes' if the the file's name is valid, and returns 'No' otherwise.
  A file's name is considered to be valid if and only if all the following conditions 
  are met:
  - There should not be more than three digits ('0'-'9') in the file's name.
  - The file's name contains exactly one dot '.'
  - The substring before the dot should not be empty, and it starts with a letter from 
  the latin alphapet ('a'-'z' and 'A'-'Z').
  - The substring after the dot should be one of these: ['txt', 'exe', 'dll']
  Examples:
  >>> (file_name_check "example.txt")
  \"Yes\"
  >>> (file_name_check \"1example.dll\")
  \"No\""
  [file_name]
  (let [s (or file_name "")
        dot-count (count (filter #(= % \.) s))
        digit-count (count (filter #(Character/isDigit %) s))]
    (if (and (= dot-count 1)
             (<= digit-count 3))
      (let [[name ext] (clojure.string/split s #"\." 2)
            first-ch (first name)]
        (if (and (not (clojure.string/blank? name))
                 first-ch
                 (Character/isLetter ^char first-ch)
                 (#{"txt" "exe" "dll"} ext))
          "Yes"
          "No"))
      "No")))