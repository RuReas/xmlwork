package net.usachev.xmlwork.storage;

import net.usachev.xmlwork.model.KeyPosition;
import net.usachev.xmlwork.model.Position;

import java.util.List;
import java.util.Map;

public interface Storage {

    List<Position> getAll();

    boolean syncDbXml(Map<KeyPosition, Position> mapPositions);
}
