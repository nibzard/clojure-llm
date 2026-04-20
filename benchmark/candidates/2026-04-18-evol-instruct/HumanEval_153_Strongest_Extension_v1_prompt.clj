(defn strongest-extension
  "Given a class name string and a possibly-nil collection of extension names, return
   the class name joined with the strongest extension.

   The strength of an extension is calculated as:
   uppercase letters count - lowercase letters count

   Ignore any characters that are neither uppercase nor lowercase letters.

   Requirements:
   - If extensions is nil or empty, return just the class name.
   - If two extensions have the same strength, choose the one that appears first.
   - Work with any sequential collection, not just vectors.
   - Example:
     (strongest-extension \"my_class\" [\"AA\" \"Be\" \"CC\"])
     => \"my_class.AA\"
   - Example:
     (strongest-extension \"core\" nil)
     => \"core\""
  [class-name extensions])